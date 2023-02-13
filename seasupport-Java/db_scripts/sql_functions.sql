create or replace function get_active_visit(login_user Varchar2)
return number
as
    visit_id number;
    current_date date;
begin
    select sysdate
    into current_date
    from DUAL;

    select v.VISIT_ID
    into visit_id
    from VISITS v
    where v.LOGIN = login_user and
    current_date > DATE_BEGIN and
    current_date < DATE_END;

    return visit_id;
end;


create or replace function popular_port(v_date_begin DATE, v_date_end DATE)
return NUMBER
AS
v_visits NUMBER := 0;
v_port_id NUMBER := 0;
BEGIN
    select count(port_id) as v_counter, port_id into v_visits, v_port_id
    from visits
    where v_date_begin BETWEEN date_begin and date_end or
    v_date_end BETWEEN date_begin and date_end
    group by port_id
    order by v_counter desc
    FETCH NEXT 1 ROWS ONLY;

    return v_port_id;
END;

create or replace function time_from_last_visit(v_login VARCHAR2)
RETURN NUMBER
as
v_days NUMBER := 0;
BEGIN

    BEGIN
    select sysdate - date_end as dif into v_days from visits
    where login = v_login
    order by dif desc
    FETCH NEXT 1 ROWS ONLY;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        v_days := -1;
    END;
    return v_days;
END;

create or replace FUNCTION user_visits_stats(user_login VARCHAR2)
return NUMBER
AS
  v_days NUMBER := 0;
BEGIN
    select sum(date_end - date_begin) into v_days
    from visits
    where login = user_login;

    return v_days;
END;
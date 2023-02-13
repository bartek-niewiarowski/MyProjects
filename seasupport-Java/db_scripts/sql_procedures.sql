create or replace procedure update_prices(v_energy NUMBER, v_adm NUMBER)
as
v_e FLOAT;
v_a FLOAT;
v_list_id NUMBER;

cursor cr is select list_id from price_list;
BEGIN
    v_e := (100 + v_energy) / 100;
    v_a := (100 + v_adm) / 100;

    open cr;
    loop
    fetch cr into v_list_id;
    exit when cr%notfound;
        UPDATE PRICE_LIST SET LAUNDRY = LAUNDRY * v_e, DRYING_ROOM = DRYING_ROOM * v_e, SAUNA = SAUNA * v_e,
        PLACE_LESS_7M = PLACE_LESS_7M * v_a, PLACE_7_12M = PLACE_7_12M * v_a, PLACE_12_17M = PLACE_12_17M * v_a,
        PLACE_17_20M = PLACE_17_20M * v_a, PLACE_MORE_20M = PLACE_MORE_20M * v_a where list_id = v_list_id;
    end loop;
    close cr;
End;


create or replace procedure remove_inactive_users as
cursor cr is select * from all_users;
v_user all_users%ROWTYPE;
v_days NUMBER := 0;
begin
    open cr;
    loop
    fetch cr into v_user;
    exit when cr%notfound;
        v_days := time_from_last_visit(v_user.login);
        if v_days > 365 then delete from all_users where login = v_user.login; end if;
    end loop;
    close cr;
end;
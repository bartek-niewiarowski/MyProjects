create or replace trigger current_visit_date
before update or insert on visits for each row
when ( new.DATE_BEGIN < sysdate )
declare
    too_old_date exception;
begin
    raise_application_error(-20002, 'Wanted to add visit with a uncurrent date');

end;


create or replace trigger correct_ship_owner
before update or insert on SHIP_OWNERS for each row
when ( not (new.FORENAME is null and new.SURNAME is null and new.PESEL is null and new.NAME_COMPANY is not null and new.NIP is not null)
    or not (new.FORENAME is not null and new.SURNAME is not null and new.PESEL is not null and new.NAME_COMPANY is null and new.NIP is null))
    -- user wanted to insert data of private or commercial owner
    -- or wanted to insert data of both private and commercial owner
declare
    faulty_commercial_owner_data exception;
    faulty_private_owner_data exception;
    faulty_data_private_commercial_data exception;
begin
    if ((:new.FORENAME is not null or :new.SURNAME is not null or :new.PESEL is not null) and (:new.NAME_COMPANY is not null or :new.NIP is not null)) then
        -- user wanted to insert data of both commercial and private owner
        raise_application_error(-20001, 'Wanted to insert data of both commercial and private owner');
    elsif (:new.FORENAME is not null or :new.SURNAME is not null or :new.PESEL is not null) then
        -- user wanted to insert not full data of private owner
        raise_application_error(-20002, 'Wanted to insert not full data of the private owner');
    else
        -- user wanted to insert not full data of commercial owner
        raise_application_error(-20003, 'Wanted to insert not full data of the commercial owner');
    end if;
end;

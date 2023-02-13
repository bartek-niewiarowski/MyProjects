--trigger correct_ship_owner
INSERT INTO ship_owners (ship_owner_id, phone_number, email, forename, surname, pesel, nip)
VALUES (ship_owners_id.nextval, '133434555', 'aagaa52@gmail.com', 'Mateusz', 'Berert', '01110303044', '1234');

INSERT INTO ship_owners (ship_owner_id, phone_number, email, forename, surname)
VALUES (ship_owners_id.nextval, '133434555', 'aagaa52@gmail.com', 'Mateusz', 'Berert');

INSERT INTO ship_owners (ship_owner_id, phone_number, email, name_company)
VALUES (ship_owners_id.nextval, '444434555', 'transpmor@gmail.com', 'Transport Morski');


--trigger current_visit_date
INSERT INTO visits VALUES ('23/01/21', '23/02/27', 1, 'jankP', 'FEdfa', 83, visit_id.nextval);

INSERT INTO visits VALUES ('23/01/11', '23/02/27', 1, 'jankP', 'FEdfa', 83, visit_id.nextval);

--procedura update_prices na przyk³adzie prania
CREATE OR replace PROCEDURE test_update_prices(v_list_id NUMBER, v_energy NUMBER, v_adm NUMBER)
AS
value_before NUMBER;
value_after NUMBER;
BEGIN
SELECT laundry INTO value_before FROM price_list WHERE list_id = v_list_id;
update_prices(v_energy, v_adm);
SELECT laundry INTO value_after FROM price_list WHERE list_id = v_list_id;
dbms_output.put_line ('Cena prania przed zmian¹: '|| value_before);
dbms_output.put_line ('Cena prania po zmianie: '|| value_after);
EXCEPTION
WHEN no_data_found THEN
dbms_output.put_line ('Niepoprawne id listy');
RAISE;
END;
/

exec test_update_prices(1, 1, 1);

--funkcja user_visits_stats
SELECT login, ROUND(user_visits_stats(login), 2)
FROM all_users
WHERE user_visits_stats(login) IS NOT NULL;

--funkcja time_from_last_visit
SELECT login, ROUND(time_from_last_visit(login), 2)
FROM all_users
WHERE time_from_last_visit(login) != -1;

SELECT login, ROUND(time_from_last_visit(login), 2)
FROM all_users
WHERE time_from_last_visit(login) > 5;

SELECT u.login, v.visit_id, c.forename as captain
FROM all_users u
RIGHT JOIN visits v ON (u.login = v.login)
LEFT JOIN captains c ON (v.captain_id = c.captain_id)
WHERE time_from_last_visit(u.login) > 5;

--funkcja popular_port
SELECT port_name
FROM ports
WHERE port_id = popular_port('23/01/01', '23/02/01');

SELECT port_name
FROM ports
WHERE port_id = popular_port('23/03/01', '23/04/01');

--funkcja get_active_visit
SELECT login, get_active_visit(login)
FROM all_users;

SELECT login, get_active_visit(login)
FROM all_users
WHERE login like '%p%';

--kilka ró¿nych zapytañ

SELECT u.login, v.visit_id, p.port_name FROM all_users u
JOIN visits v ON (u.login = v.login)
JOIN ports p ON (p.port_id = v.port_id)
WHERE p.port_id = popular_port('23/01/01', '23/02/01');


SELECT login, count(*) FROM all_users
JOIN visits USING (login)
GROUP BY login;

SELECT login, count(*) FROM all_users
JOIN visits USING (login)
GROUP BY login
ORDER BY count(*) DESC
FETCH NEXT 1 ROW ONLY;

SELECT * FROM ships
WHERE (ship_owner_id IN (SELECT ship_owner_id FROM ship_owners
WHERE phone_number LIKE '%4555%')) AND ship_type = 'sailing_boat'
ORDER BY ship_name;
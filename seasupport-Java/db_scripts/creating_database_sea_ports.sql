CREATE TABLE all_users
(
    login VARCHAR2(15) CONSTRAINT login_pk PRIMARY KEY,
    user_password VARCHAR2(15) NOT NULL,
    forename VARCHAR2(15 CHAR) NOT NULL,
    surname VARCHAR2(15 CHAR) NOT NULL,
    phone_number VARCHAR2(13) NOT NULL,
    birthday DATE NOT NULL,
    pesel VARCHAR2(11 BYTE),
    user_type VARCHAR2(6 CHAR) CONSTRAINT two_user_types CHECK (user_type = 'admin' OR user_type = 'normal')
);


CREATE TABLE ship_owners
(
    ship_owner_id NUMBER(7) CONSTRAINT ship_owners_pk PRIMARY KEY,
    phone_number VARCHAR2(13 BYTE) NOT NULL,
    email VARCHAR2(35 CHAR) NOT NULL CONSTRAINT email_owner_unique UNIQUE,
    forename VARCHAR2(15 CHAR),
    surname VARCHAR2(15 CHAR),
    pesel VARCHAR2(11 BYTE) CONSTRAINT pesel_unique_owners UNIQUE,
    name_company VARCHAR(30 CHAR),
    NIP NUMBER(10)
);

CREATE TABLE ships
(
    call_sign VARCHAR2(15 CHAR) CONSTRAINT ships_pk PRIMARY KEY,
    ship_name VARCHAR2(15 CHAR) NOT NULL,
    
    ship_owner_id NUMBER(7) NOT NULL CONSTRAINT ship_owners_fk REFERENCES ship_owners(ship_owner_id),
    ship_length NUMBER(5, 2) NOT NULL,
    ship_type VARCHAR2(12) NOT NULL CONSTRAINT only_2_types CHECK (ship_type = 'sailing_boat' OR ship_type = 'motor_boat')

);

CREATE TABLE captains
(
    forename VARCHAR2(15 CHAR) NOT NULL,
    surname VARCHAR2(15 CHAR) NOT NULL,
    pesel VARCHAR2(11 BYTE) NOT NULL CONSTRAINT pesel_unique UNIQUE,
    captain_id NUMBER(7) CONSTRAINT captain_pk PRIMARY KEY
);

CREATE TABLE price_list
(
    list_id NUMBER(7) CONSTRAINT price_list_pk PRIMARY KEY,
    laundry NUMBER(4, 2),
    drying_room NUMBER(4, 2),
    water NUMBER(4, 2),
    shower NUMBER(4, 2),
    sauna NUMBER(4, 2),
    place_less_7m NUMBER(6, 2),
    place_7_12m NUMBER(6, 2),
    place_12_17m NUMBER(6, 2),
    place_17_20m NUMBER(6, 2),
    place_more_20m NUMBER(6, 2)
);


CREATE TABLE ports
(
    port_id NUMBER(7) CONSTRAINT port_pk PRIMARY KEY,
    port_name VARCHAR2(50 CHAR) NOT NULL CONSTRAINT unique_port_name UNIQUE,
    places_ships_big NUMBER(3) NOT NULL,
    places_ships_small NUMBER(3) NOT NULL,
    street_number NUMBER(3) NOT NULL,
    street_name VARCHAR2(20 CHAR) NOT NULL,
    city_name VARCHAR2(15 CHAR) NOT NULL,
    post_code VARCHAR2(6 BYTE) NOT NULL,
    phone_number VARCHAR2(13) NOT NULL,
    vhf_channel NUMBER(2) NOT NULL,
    bank_account VARCHAR2(30 BYTE),
    price_list_id NUMBER(7) NOT NULL CONSTRAINT ports_price_fk REFERENCES price_list(list_id)
);




CREATE TABLE admin_port_intermediary
(
        login VARCHAR2(15 CHAR) NOT NULL CONSTRAINT inter_admin_fk REFERENCES all_users(login),
        port_id NUMBER(7) NOT NULL CONSTRAINT inter_port_pk REFERENCES ports(port_id),
        CONSTRAINT inter_admin_port_pk PRIMARY KEY (login, port_id)
);


CREATE TABLE visits
(
    date_begin DATE NOT NULL,
    date_end DATE,
    port_id NUMBER(7) NOT NULL CONSTRAINT visits_ports_fk REFERENCES ports(port_id),
    login VARCHAR2(15) NOT NULL CONSTRAINT visits_user_fk REFERENCES all_users(login),
    call_sign VARCHAR2(15 CHAR) NOT NULL CONSTRAINT visits_ships_fk REFERENCES ships(call_sign),
    captain_id NUMBER(7) NOT NULL CONSTRAINT visits_captains_fk REFERENCES captains(captain_id),
    visit_id NUMBER(7) CONSTRAINT vistits_pk PRIMARY KEY,
    CONSTRAINT unique_visit UNIQUE (port_id, login, call_sign, captain_id, date_begin)
);

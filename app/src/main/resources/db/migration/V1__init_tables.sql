CREATE TABLE coffee_type(
    id INT NOT NULL,
    type_name VARCHAR(200) NOT NULL,
    price DOUBLE NOT NULL,
    disabled CHAR(1),
    PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Лишний индекс?
CREATE INDEX CT_I on coffee_type(
    id ASC
);

CREATE TABLE coffee_order(
    id INT NOT NULL,
    order_date DATETIME NOT NULL,
    name VARCHAR(100),
    delivery_address VARCHAR(200) NOT NULL,
    cost DOUBLE,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Лишний индекс?
CREATE INDEX CO_I1 on coffee_order(
    id ASC
);

CREATE TABLE coffee_order_item(
    id INT NOT NULL,
    type_id INT NOT NULL,
    order_id INT NOT NULL,
    quantity INT,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE INDEX COI_I on coffee_order_item(
    order_id ASC
);

CREATE INDEX COI_3 on coffee_order_item(
    type_id ASC
);

ALTER TABLE coffee_order_item
    ADD CONSTRAINT COI_CO foreign key(order_id)
        REFERENCES coffee_order(id);

ALTER TABLE coffee_order_item
    ADD CONSTRAINT COI_CT foreign key(type_id)
        REFERENCES coffee_type(id);

CREATE TABLE configuration(
    id VARCHAR(20) NOT NULL,
    value VARCHAR(30),
    PRIMARY KEY(id)
) ENGINE=InnoDB;

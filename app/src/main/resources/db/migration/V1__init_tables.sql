CREATE TABLE CoffeeType(
    id INT NOT NULL,
    type_name VARCHAR(200) NOT NULL,
    price DOUBLE NOT NULL,
    disabled CHAR(1),
    PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Лишний индекс?
CREATE INDEX CT_I on CoffeeType(
    id ASC
);

CREATE TABLE CoffeeOrder(
    id INT NOT NULL,
    order_date DATETIME NOT NULL,
    name VARCHAR(100),
    delivery_address VARCHAR(200) NOT NULL,
    cost DOUBLE,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Лишний индекс?
CREATE INDEX CO_I1 on CoffeeOrder(
    id ASC
);

CREATE TABLE CoffeeOrderItem(
    id INT NOT NULL,
    type_id INT NOT NULL,
    order_id INT NOT NULL,
    quantity INT,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE INDEX COI_I on CoffeeOrderItem(
    order_id ASC
);

CREATE INDEX COI_3 on CoffeeOrderItem(
    type_id ASC
);

ALTER TABLE CoffeeOrderItem
    ADD CONSTRAINT COI_CO foreign key(order_id)
        REFERENCES CoffeeOrder(id);

ALTER TABLE CoffeeOrderItem
    ADD CONSTRAINT COI_CT foreign key(type_id)
        REFERENCES CoffeeType(id);

CREATE TABLE Configuration(
    id VARCHAR(20) NOT NULL,
    value VARCHAR(30),
    PRIMARY KEY(id)
) ENGINE=InnoDB;
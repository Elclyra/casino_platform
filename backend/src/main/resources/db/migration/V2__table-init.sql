CREATE TABLE transaction
(
    id         UUID           NOT NULL,
    wallet_id  UUID           NOT NULL,
    amount     DECIMAL(19, 2) NOT NULL,
    type       VARCHAR(255)   NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

CREATE TABLE "users"
(
    id            UUID         NOT NULL,
    username      VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255),
    email         VARCHAR(255),
    user_type     VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE wallet
(
    id       UUID           NOT NULL,
    user_id  UUID           NOT NULL,
    balance  DECIMAL(19, 2) NOT NULL,
    currency VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_wallet PRIMARY KEY (id)
);

ALTER TABLE "users"
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE wallet
    ADD CONSTRAINT uc_wallet_user UNIQUE (user_id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_WALLET FOREIGN KEY (wallet_id) REFERENCES wallet (id);

ALTER TABLE wallet
    ADD CONSTRAINT FK_WALLET_ON_USER FOREIGN KEY (user_id) REFERENCES "users" (id);
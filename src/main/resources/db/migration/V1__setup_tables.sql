CREATE SCHEMA IF NOT EXISTS bank_transfer;

CREATE TABLE IF NOT EXISTS bank_transfer.customer (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_customer_id PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS bank_transfer.account(
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    customer_id UUID NOT NULL,
    balance BIGINT NOT NULL,
    CONSTRAINT pk_account_id PRIMARY KEY(id),
    CONSTRAINT fk_account_customer_id FOREIGN KEY (customer_id) REFERENCES bank_transfer.customer(id)
);

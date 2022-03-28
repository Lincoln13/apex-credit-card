CREATE TABLE PUBLIC.CCI_CREDIT_CARD_INFO (
    CCI_PK INTEGER NOT NULL,
    CREDIT_BALANCE DECIMAL(19,2),
    CREATED_DATE TIMESTAMP,
    CREDIT_CARD_NAME VARCHAR(255),
    CREDIT_CARD_NUMBER VARCHAR(255),
    IS_DELETED TIMESTAMP,
    CREDIT_LIMIT DECIMAL(19,2),
    PRIMARY KEY (CCI_PK)
);

INSERT INTO CCI_CREDIT_CARD_INFO(cci_pk, credit_card_name, credit_card_number, credit_balance,
credit_limit, is_deleted, created_date) VALUES (1, 'sabyasachi', '1203-1300-1500-9000',
1500, 5000, null, '2038-01-19 03:14:07.999999');

INSERT INTO CCI_CREDIT_CARD_INFO(cci_pk, credit_card_name, credit_card_number, credit_balance,
credit_limit, is_deleted, created_date) VALUES (2, 'rohan', '1203-1300-1500-9000',
1500, 5000, null, '2038-01-19 03:14:07.999999');

INSERT INTO CCI_CREDIT_CARD_INFO(cci_pk, credit_card_name, credit_card_number, credit_balance,
credit_limit, is_deleted, created_date) VALUES (3, 'shyam', '1203-1300-1500-9000',
1500, 5000, null, '2038-01-19 03:14:07.999999');
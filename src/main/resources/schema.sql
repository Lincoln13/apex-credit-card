CREATE TABLE PUBLIC.CCI_CREDIT_CARD_INFO (
    CCI_PK INTEGER NOT NULL,
    CREDIT_BALANCE DECIMAL(19,2),
    CREATED_DATE TIMESTAMP,
    CREDIT_CARD_NAME VARCHAR(255),
    CREDIT_CARD_NUMBER VARCHAR(255),
    CREDIT_LIMIT DECIMAL(19,2),
    PRIMARY KEY (CCI_PK)
);

CREATE SEQUENCE sequencer
  START WITH 10
  INCREMENT BY 1;
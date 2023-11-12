CREATE TABLE IF NOT EXISTS sample (
    id VARCHAR(36) NOT NULL UNIQUE,
    CONSTRAINT sample_pk PRIMARY KEY (id)
);

ALTER TABLE sample owner TO application_api_username;
GRANT ALL ON TABLE sample TO application_api_username;
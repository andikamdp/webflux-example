CREATE TABLE "public".tbl_employee_tm
(
    id          UUID PRIMARY KEY                  default gen_random_uuid(),
    identity_id VARCHAR(255)             NOT NULL,
    "name"      VARCHAR(255)             NOT NULL,
    birth_date  DATE                     NOT NULL,
    join_date   DATE                     NOT NULL,
    salary      NUMERIC(19, 2)           NOT NULL,
    status      INT                      NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);
CREATE INDEX idx_employee_identity_id ON "public".tbl_employee_tm (identity_id);
CREATE INDEX idx_employee_name ON "public".tbl_employee_tm (name);
CREATE INDEX idx_employee_birth_date ON "public".tbl_employee_tm (birth_date);
CREATE INDEX idx_employee_salary ON "public".tbl_employee_tm (salary);
CREATE INDEX idx_employee_status ON "public".tbl_employee_tm (status);

CREATE TABLE "public".tbl_holiday_tm
(
    id          UUID PRIMARY KEY                  default gen_random_uuid(),
    holiday_date      TIMESTAMP                NOT NULL,
    description VARCHAR(255)             NOT NULL,
    status      INT                      NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);
CREATE INDEX idx_holiday_date ON "public".tbl_holiday_tm (holiday_date);

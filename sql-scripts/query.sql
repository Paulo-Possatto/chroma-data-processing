CREATE SCHEMA IF NOT EXISTS report;

CREATE TABLE report.transformer_data (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	actual_load int4 NOT NULL,
	"configuration" int4 NULL,
	fabrication_date varchar(255) NOT NULL,
	installation_date varchar(255) NOT NULL,
	isolation_type int4 NOT NULL,
	last_maintenance_date varchar(255) NOT NULL,
	"location" varchar(255) NOT NULL,
	max_operating_temp int4 NOT NULL,
	nominal_current float4 NOT NULL,
	nominal_power varchar(255) NOT NULL,
	phase int4 NOT NULL,
	primary_voltage varchar(255) NOT NULL,
	refrigeration_type int4 NOT NULL,
	secondary_voltage varchar(255) NOT NULL,
	serial_number varchar(255) NOT NULL,
	status int4 NOT NULL,
	transformer_frequency int4 NOT NULL,
	transformer_id varchar(255) NOT NULL,
	transformer_impedance float4 NOT NULL,
	transformer_manufacturer varchar(255) NOT NULL,
	transformer_name varchar(255) NOT NULL,
	transformer_type int4 NOT NULL,
	CONSTRAINT transformer_data_pkey PRIMARY KEY (id)
);

CREATE TABLE report.transformer_analysis (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	analysis_timestamp timestamp(6) NOT NULL,
	c2h2_ppm int4 NOT NULL,
	c2h4_ppm int4 NOT NULL,
	c2h6_ppm int4 NOT NULL,
	ch4_ppm int4 NOT NULL,
	co_ppm int4 NOT NULL,
	co2_ppm int4 NOT NULL,
	h2_ppm int4 NOT NULL,
	oil_acidity float8 NOT NULL,
	oil_pressure float8 NOT NULL,
	oil_temperature int4 NOT NULL,
	transformer_id int8 NOT NULL,
	CONSTRAINT transformer_analysis_pkey PRIMARY KEY (id),
	CONSTRAINT fk6ti4kt4u155kaw2blwrty6n0n FOREIGN KEY (transformer_id) REFERENCES report.transformer_data(id)
);

CREATE TABLE report.transformer_results (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	analysis_method int4 NOT NULL,
	analysis_result varchar(255) NOT NULL,
	result_identification uuid NOT NULL,
	transformer_id int8 NOT NULL,
	CONSTRAINT transformer_results_pkey PRIMARY KEY (id),
	CONSTRAINT uktm91vusli03ps7j4n4wmdgm2m UNIQUE (transformer_id),
	CONSTRAINT fkbuav3nnsyvavnc2decr03idh6 FOREIGN KEY (transformer_id) REFERENCES report.transformer_analysis(id)
);
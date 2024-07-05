CREATE EXTENSION "uuid-ossp";

 CREATE OR REPLACE FUNCTION before_update_updated_at() RETURNS trigger AS
 $BODY$
 BEGIN
     IF row(NEW.*::text) IS DISTINCT FROM row(OLD.*::text) THEN
         NEW.updated_at = now();
     END IF;

     RETURN NEW;
 END;
 $BODY$

 LANGUAGE plpgsql;


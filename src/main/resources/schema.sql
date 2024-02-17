DROP TABLE IF EXISTS prestamos;
DROP SEQUENCE IF EXISTS prestamos_seq;

CREATE SEQUENCE prestamos_seq;
ALTER SEQUENCE prestamos_seq INCREMENT BY 1;

CREATE TABLE prestamos (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(10),
    identificacion_usuario VARCHAR(10),
    tipo_usuario INTEGER,
    fecha_maxima_devolucion DATE  );
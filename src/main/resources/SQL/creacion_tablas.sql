CREATE TABLE movimiento (
  id INT NOT NULL AUTO_INCREMENT,
  id_empresa INT NOT NULL,
  descripcion VARCHAR(255),
  bodega_origen_codigo VARCHAR(20),
  bodega_destino_codigo VARCHAR(20),
  fecha_creacion DATE,
  fecha_entrega DATE,
  estado VARCHAR(1) DEFAULT 'P',
  PRIMARY KEY (id)
);

CREATE TABLE movimiento_detalle (
  id INT NOT NULL AUTO_INCREMENT,
  movimiento_id INT NOT NULL,
  item_codigo VARCHAR(20),
  cantidad_enviada INT,
  PRIMARY KEY (id),
  FOREIGN KEY (movimiento_id)
   REFERENCES movimiento (id)
);
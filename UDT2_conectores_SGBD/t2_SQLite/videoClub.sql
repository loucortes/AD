-- Crear la base de datos
CREATE DATABASE videoclub;
USE videoclub;

-- Tabla de clientes
CREATE TABLE clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100),
    fecha_registro DATE 
);

-- Tabla de películas
CREATE TABLE peliculas (
    pelicula_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    director VARCHAR(100),
    anio_lanzamiento YEAR,
    duracion INT, -- duración en minutos
    stock INT DEFAULT 0, -- cantidad de películas disponibles
    fecha_agregado DATE 
);

-- Tabla de categorías de películas
CREATE TABLE categorias (
    categoria_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

-- Tabla intermedia para relacionar películas con categorías (muchos a muchos)
CREATE TABLE peliculas_categorias (
    pelicula_id INT,
    categoria_id INT,
    PRIMARY KEY (pelicula_id, categoria_id),
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(pelicula_id) ON DELETE CASCADE,
    FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id) ON DELETE CASCADE
);

-- Tabla de empleados
CREATE TABLE empleados (
    empleado_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    puesto VARCHAR(50),
    fecha_contratacion DATE 
);

-- Tabla de alquileres
CREATE TABLE alquileres (
    alquiler_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    pelicula_id INT,
    empleado_id INT,
    fecha_alquiler DATE,
    fecha_devolucion DATE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(pelicula_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id)
);
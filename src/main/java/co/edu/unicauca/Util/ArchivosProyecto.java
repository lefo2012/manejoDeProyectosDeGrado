package co.edu.unicauca.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.Normalizer;

public class ArchivosProyecto {

    // 20 MB
    private static final long MAX_BYTES = 20L * 1024 * 1024;

    /**
     * Copia un archivo a una subcarpeta del proyecto con el nombre indicado.
     * Retorna la ruta relativa final guardada (por ejemplo: "uploads/proyectos/mi_formato.pdf").
     */
    public static String guardarArchivoEnProyecto(File origen,
                                                  String nombreDestinoSinExt,
                                                  String subcarpetaRelativa) throws IOException {
        if (origen == null || !origen.exists()) {
            throw new IllegalArgumentException("No hay archivo de origen.");
        }

        // Validaciones (PDF y tamaño)
        String ext = obtenerExtension(origen.getName()).toLowerCase();
        if (!ext.equals(".pdf")) {
            throw new IllegalArgumentException("El archivo debe ser un PDF.");
        }
        if (origen.length() > MAX_BYTES) {
            throw new IllegalArgumentException("El PDF supera los 20 MB.");
        }

        // Normaliza el nombre destino y asegura extensión .pdf
        String base = normalizarNombre(nombreDestinoSinExt);
        if (base.isBlank()) base = "formato";
        String nombreFinal = base + ".pdf";

        // Carpeta del proyecto (directorio de trabajo) + subcarpeta deseada
        Path dirProyecto = Paths.get(System.getProperty("user.dir"));
        Path dirDestino = dirProyecto.resolve(subcarpetaRelativa);
        Files.createDirectories(dirDestino);

        // Evita colisiones de nombre
        Path destino = dirDestino.resolve(nombreFinal);
        int i = 1;
        while (Files.exists(destino)) {
            nombreFinal = base + "_" + i + ".pdf";
            destino = dirDestino.resolve(nombreFinal);
            i++;
        }

        // Copia
        Files.copy(origen.toPath(), destino, StandardCopyOption.COPY_ATTRIBUTES);

        // Devuelve ruta relativa (útil para guardar en BD)
        Path rutaRelativa = dirProyecto.relativize(destino);
        return rutaRelativa.toString().replace("\\", "/");
    }

    private static String obtenerExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot >= 0) ? fileName.substring(dot) : "";
    }

    private static String normalizarNombre(String s) {
        // Quita acentos y caracteres problemáticos, deja [a-zA-Z0-9-_]
        String n = Normalizer.normalize(s == null ? "" : s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        n = n.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        // evita comenzar por punto y recorta
        n = n.replaceAll("^\\.+", "").replaceAll("_+", "_").trim();
        if (n.endsWith(".")) n = n.substring(0, n.length() - 1);
        // sin extensión aquí; solo base
        int dot = n.lastIndexOf('.');
        return (dot > 0) ? n.substring(0, dot) : n;
    }
}

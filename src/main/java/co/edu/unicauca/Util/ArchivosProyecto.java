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
    public static boolean validarArchivo(File archivoAValidar)
    {

       if (archivoAValidar == null || !archivoAValidar.exists() || !archivoAValidar.isFile()) {
           return false;
       }
       
       String nombreArchivo = archivoAValidar.getName().toLowerCase();
       if (!nombreArchivo.endsWith(".pdf")) {
           return false;
       }
       
       
       long tamañoMaximo = 20 * 1024 * 1024;
       long tamañoArchivo = archivoAValidar.length();
       
       if (tamañoArchivo > tamañoMaximo) {
           return false;
       }
       return true;
       
    }
    public static boolean guardarArchivoEnProyecto(File origen,String nombreDestinoSinExt,String subcarpetaRelativa) throws Exception {

        if(!validarArchivo(origen))
        {
            throw new Exception("Archivo no valido"); 
        }

        // Asegura la carpeta destino
        Path dirProyecto = Paths.get(System.getProperty("user.dir"));
        Path dirDestino  = dirProyecto.resolve(subcarpetaRelativa);
        Files.createDirectories(dirDestino);

        
        String nombreFinal = verificarDuplicidad(nombreDestinoSinExt, subcarpetaRelativa);

        Path destino = dirDestino.resolve(nombreFinal);
        
        Files.copy(origen.toPath(), destino, StandardCopyOption.COPY_ATTRIBUTES);

        return true;
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
    public static String verificarNombreArchivo(String nombreDestinoSinExt,String subcarpetaRelativa) throws IOException {
        String base = normalizarNombre(nombreDestinoSinExt);
        if (base.isBlank()) base = "formato";

        Path dirProyecto = Paths.get(System.getProperty("user.dir"));
        Path dirDestino  = dirProyecto.resolve(subcarpetaRelativa);
        Files.createDirectories(dirDestino);

        String nombreFinal = base + ".pdf";
        Path destino = dirDestino.resolve(nombreFinal);

        int i = 1;
        while (Files.exists(destino)) {
            nombreFinal = base + "_" + i + ".pdf";
            destino = dirDestino.resolve(nombreFinal);
            i++;
        }
        Path rutaRelativa = dirProyecto.relativize(destino);
        return rutaRelativa.toString().replace("\\", "/");
    }
    private static String verificarDuplicidad(String nombreDestinoSinExt,String subcarpetaRelativa) throws IOException {
        String base = normalizarNombre(nombreDestinoSinExt);
        if (base.isBlank()) base = "formato";

        Path dirProyecto = Paths.get(System.getProperty("user.dir"));
        Path dirDestino  = dirProyecto.resolve(subcarpetaRelativa);
        Files.createDirectories(dirDestino);

        String nombreFinal = base + ".pdf";
        Path destino = dirDestino.resolve(nombreFinal);

        int i = 1;
        while (Files.exists(destino)) {
            nombreFinal = base + "_" + i + ".pdf";
            destino = dirDestino.resolve(nombreFinal);
            i++;
        }
        return nombreFinal;
    }
}

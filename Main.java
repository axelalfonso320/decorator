public class Main {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Axel");
        usuario.activarSuscripcion(ServicioStreaming.DISNEY);
        usuario.activarSuscripcion(ServicioStreaming.WARNER);

        CatalogoPeliculas disney = new SuscripcionDecorator(new CatalogoDisney(), usuario);
        CatalogoPeliculas paramount = new SuscripcionDecorator(new CatalogoParamount(), usuario);
        CatalogoPeliculas warner = new SuscripcionDecorator(new CatalogoWarner(), usuario);

        System.out.println("=== Suscripciones iniciales: Disney y Warner ===");
        mostrarCatalogo(disney);
        mostrarCatalogo(paramount);
        mostrarCatalogo(warner);

        System.out.println("\n=== Axel activa Paramount ===");
        usuario.activarSuscripcion(ServicioStreaming.PARAMOUNT);
        mostrarCatalogo(paramount);

        System.out.println("\n=== Axel cancela Disney ===");
        usuario.cancelarSuscripcion(ServicioStreaming.DISNEY);
        mostrarCatalogo(disney);

        System.out.println("\n=== Axel vuelve a activar Disney ===");
        usuario.activarSuscripcion(ServicioStreaming.DISNEY);
        mostrarCatalogo(disney);
    }

    private static void mostrarCatalogo(CatalogoPeliculas catalogo) {
        System.out.println("\nCatalogo " + catalogo.getServicio().getNombre() + ":");
        try {
            for (String pelicula : catalogo.obtenerPeliculas()) {
                System.out.println("- " + pelicula);
            }
        } catch (IllegalStateException error) {
            System.out.println("Acceso denegado: " + error.getMessage());
        }
    }
}
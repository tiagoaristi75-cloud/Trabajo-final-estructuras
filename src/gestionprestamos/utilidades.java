import java.util.Scanner;

/// Menus y operaciones que llama el main. 

public class utilidades {
    private validaciones val = new validaciones();

    // ===================== MENUS (HU5) =====================

    public int menu(Scanner sc) {
        System.out.println("\n========= RENTING DE VEHICULOS =========");
        System.out.println("1. GESTION DE CLIENTES");
        System.out.println("2. GESTION DE VEHICULOS");
        System.out.println("3. GESTION DE CONTRATOS DE RENTING");
        System.out.println("4. IMPRIMIR INFORME GENERAL");
        System.out.println("5. SALIR DEL SISTEMA");
        return val.ValidaRango(sc, "Elija una opcion: ", 1, 5);
    }

    public void gestionClientes(Renting r, Scanner sc) {
        int op;
        do {
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1.1 Registrar cliente");
            System.out.println("1.2 Modificar cliente");
            System.out.println("1.3 Eliminar cliente");
            System.out.println("1.4 Buscar cliente");
            System.out.println("1.5 Volver al menu principal");
            op = val.ValidaRango(sc, "Elija (1-5): ", 1, 5);
            switch (op) {
                case 1: registrarCliente(r, sc); break;
                case 2: modificarCliente(r, sc); break;
                case 3: eliminarCliente(r, sc); break;
                case 4: buscarCliente(r, sc); break;
                default: break;
            }
        } while (op != 5);
    }

    public void gestionVehiculos(Renting r, Scanner sc) {
        int op;
        do {
            System.out.println("\n--- GESTION DE VEHICULOS ---");
            System.out.println("2.1 Registrar vehiculo");
            System.out.println("2.2 Modificar vehiculo");
            System.out.println("2.3 Eliminar vehiculo");
            System.out.println("2.4 Buscar vehiculo");
            System.out.println("2.5 Volver al menu principal");
            op = val.ValidaRango(sc, "Elija (1-5): ", 1, 5);
            switch (op) {
                case 1: registrarVehiculo(r, sc); break;
                case 2: modificarVehiculo(r, sc); break;
                case 3: eliminarVehiculo(r, sc); break;
                case 4: buscarVehiculo(r, sc); break;
                default: break;
            }
        } while (op != 5);
    }

    public void gestionContratos(Renting r, Scanner sc) {
        int op;
        do {
            System.out.println("\n--- GESTION DE CONTRATOS DE RENTING ---");
            System.out.println("3.1 Registrar nuevo contrato");
            System.out.println("3.2 Modificar contrato");
            System.out.println("3.3 Finalizar contrato (devolucion)");
            System.out.println("3.4 Buscar contrato");
            System.out.println("3.5 Volver al menu principal");
            op = val.ValidaRango(sc, "Elija (1-5): ", 1, 5);
            switch (op) {
                case 1: registrarContrato(r, sc); break;
                case 2: modificarContrato(r, sc); break;
                case 3: finalizarContrato(r, sc); break;
                case 4: buscarContrato(r, sc); break;
                default: break;
            }
        } while (op != 5);
    }

    // ===================== CLIENTES =====================

    private void registrarCliente(Renting r, Scanner sc) {
        String ced = val.ValidaCedula(sc, "Cedula: ");
        if (r.buscarCliente(ced) != null) {
            System.out.println("Ya existe un cliente con esa cedula.");
            return;
        }
        String nom = val.ValidaNombre(sc, "Nombre: ");
        String tel = val.ValidaTelefono(sc, "Telefono: ");
        r.agregarCliente(new Cliente(ced, nom, tel));
        System.out.println("Cliente registrado.");
    }

    private void modificarCliente(Renting r, Scanner sc) {
        Cliente c = r.buscarCliente(val.ValidaCedula(sc, "Cedula del cliente: "));
        if (c == null) {
            System.out.println("No se encontro el cliente.");
            return;
        }
        System.out.println("(La cedula no se puede modificar.)");
        c.setNombre(val.ValidaNombre(sc, "Nuevo nombre: "));
        c.setTelefono(val.ValidaTelefono(sc, "Nuevo telefono: "));
        System.out.println("Cliente modificado.");
    }

    private void eliminarCliente(Renting r, Scanner sc) {
        String ced = val.ValidaCedula(sc, "Cedula del cliente: ");
        if (r.eliminarCliente(ced)) {
            System.out.println("Cliente eliminado junto con sus contratos.");
        } else {
            System.out.println("No se encontro el cliente.");
        }
    }

    private void buscarCliente(Renting r, Scanner sc) {
        Cliente c = r.buscarCliente(val.ValidaCedula(sc, "Cedula del cliente: "));
        if (c == null) System.out.println("No se encontro el cliente.");
        else imprimir(c);
    }

    // ===================== VEHICULOS =====================

    private void registrarVehiculo(Renting r, Scanner sc) {
        String placa = val.ValidaPlaca(sc, "Placa: ");
        if (r.buscarVehiculo(placa) != null) {
            System.out.println("Ya existe un vehiculo con esa placa.");
            return;
        }
        String marca = val.ValidaTexto(sc, "Marca: ");
        String modelo = val.ValidaTexto(sc, "Modelo: ");
        float valor = val.ValidaDecimal(sc, "Valor base por dia: ", 1, 100000000);
        System.out.println("1. Automovil (sin recargo)");
        System.out.println("2. Camioneta (recargo del 15%)");
        Vehiculo nuevo;
        if (val.ValidaRango(sc, "Tipo de vehiculo (1-2): ", 1, 2) == 1) {
            nuevo = new Automovil(placa, marca, modelo, valor);
        } else {
            nuevo = new Camioneta(placa, marca, modelo, valor);
        }
        r.agregarVehiculo(nuevo);
        System.out.println("Vehiculo registrado.");
    }

    private void modificarVehiculo(Renting r, Scanner sc) {
        Vehiculo v = r.buscarVehiculo(val.ValidaPlaca(sc, "Placa del vehiculo: "));
        if (v == null) {
            System.out.println("No se encontro el vehiculo.");
            return;
        }
        System.out.println("(La placa no se puede modificar.)");
        v.setMarca(val.ValidaTexto(sc, "Nueva marca: "));
        v.setModelo(val.ValidaTexto(sc, "Nuevo modelo: "));
        v.setValorBase(val.ValidaDecimal(sc, "Nuevo valor base por dia: ", 1, 100000000));
        System.out.println("Vehiculo modificado.");
    }

    private void eliminarVehiculo(Renting r, Scanner sc) {
        String placa = val.ValidaPlaca(sc, "Placa del vehiculo: ");
        if (r.eliminarVehiculo(placa)) {
            System.out.println("Vehiculo eliminado junto con sus contratos.");
        } else {
            System.out.println("No se encontro el vehiculo.");
        }
    }

    private void buscarVehiculo(Renting r, Scanner sc) {
        Vehiculo v = r.buscarVehiculo(val.ValidaPlaca(sc, "Placa del vehiculo: "));
        if (v == null) System.out.println("No se encontro el vehiculo.");
        else imprimir(v);
    }

    // ===================== CONTRATOS (HU4) =====================

    private void registrarContrato(Renting r, Scanner sc) {
        String ced = val.ValidaCedula(sc, "Cedula del cliente: ");
        String placa = val.ValidaPlaca(sc, "Placa del vehiculo: ");
        String inicio = val.ValidaFecha(sc, "Fecha de inicio (aaaa-mm-dd): ");
        int dias = val.ValidaRango(sc, "Duracion en dias (1-365): ", 1, 365);
        String error = r.registrarContrato(ced, placa, inicio, dias);
        if (error == null) {
            System.out.println("Contrato registrado.");
            imprimir(r.contratoActivoDe(ced));
        } else {
            System.out.println("No se pudo registrar: " + error);
        }
    }

    private void modificarContrato(Renting r, Scanner sc) {
        ContratoRenting k = r.buscarContrato(val.ValidaTexto(sc, "Id del contrato: "));
        if (k == null) {
            System.out.println("No se encontro el contrato.");
            return;
        }
        System.out.println("(Solo se pueden modificar la fecha de inicio, la de fin o la duracion.)");
        System.out.println("1. Cambiar fecha de inicio (mantiene la duracion)");
        System.out.println("2. Cambiar fecha de fin");
        System.out.println("3. Cambiar duracion en dias");
        int op = val.ValidaRango(sc, "Elija (1-3): ", 1, 3);
        String inicio = k.getFechaInicio();
        String fin = k.getFechaFin();
        if (op == 1) {
            inicio = val.ValidaFecha(sc, "Nueva fecha de inicio (aaaa-mm-dd): ");
            fin = java.time.LocalDate.parse(inicio).plusDays(k.getTotalDias()).toString();
        } else if (op == 2) {
            fin = val.ValidaFecha(sc, "Nueva fecha de fin (aaaa-mm-dd): ");
        } else {
            int dias = val.ValidaRango(sc, "Nueva duracion en dias (1-365): ", 1, 365);
            fin = java.time.LocalDate.parse(inicio).plusDays(dias).toString();
        }
        String error = r.modificarContrato(k.getIdContrato(), inicio, fin);
        if (error == null) {
            System.out.println("Contrato modificado.");
            imprimir(k);
        } else {
            System.out.println("No se pudo modificar: " + error);
        }
    }

    private void finalizarContrato(Renting r, Scanner sc) {
        String error = r.finalizarContrato(val.ValidaTexto(sc, "Id del contrato: "));
        System.out.println(error == null ? "Contrato finalizado. Vehiculo disponible." : error);
    }

    private void buscarContrato(Renting r, Scanner sc) {
        ContratoRenting k = r.buscarContrato(val.ValidaTexto(sc, "Id del contrato: "));
        if (k == null) System.out.println("No se encontro el contrato.");
        else imprimir(k);
    }

    // ===================== IMPRESION (HU7) - metodos sobrecargados =====================

    public void imprimir(Cliente c) {
        System.out.println(c.resumen());
    }

    public void imprimir(Vehiculo v) {
        System.out.println("Vehiculo | " + v.getTipo() + " | Placa: " + v.getPlaca() + " | " + v.getMarca()
                + " " + v.getModelo() + " | $" + v.getValorDia() + "/dia | Estado: " + v.getEstado());
    }

    public void imprimir(ContratoRenting k) {
        System.out.println("Contrato | Id: " + k.getIdContrato() + " | Cliente: " + k.getCedulaCliente()
                + " | Placa: " + k.getPlacaVehiculo() + " | " + k.getFechaInicio() + " a " + k.getFechaFin()
                + " | Dias: " + k.getTotalDias() + " | Valor: $" + k.getValorTotal()
                + " | " + (k.isActivo() ? "ACTIVO" : "FINALIZADO"));
    }

    public void imprimirInforme(Renting r) {
        System.out.println("\n=============== INFORME GENERAL ===============");
        System.out.println("--- CLIENTES (" + r.getClientes().size() + ") ---");
        if (r.getClientes().isEmpty()) System.out.println("Sin registros.");
        for (Cliente c : r.getClientes()) imprimir(c);

        System.out.println("\n--- VEHICULOS (" + r.getVehiculos().size() + ") ---");
        if (r.getVehiculos().isEmpty()) System.out.println("Sin registros.");
        for (Vehiculo v : r.getVehiculos()) imprimir(v);

        System.out.println("\n--- CONTRATOS ACTIVOS ---");
        int activos = 0;
        for (ContratoRenting k : r.getContratos()) {
            if (k.isActivo()) { imprimir(k); activos++; }
        }
        if (activos == 0) System.out.println("Sin contratos activos.");

        System.out.println("\n--- CONTRATOS FINALIZADOS ---");
        int finalizados = 0;
        for (ContratoRenting k : r.getContratos()) {
            if (!k.isActivo()) { imprimir(k); finalizados++; }
        }
        if (finalizados == 0) System.out.println("Sin contratos finalizados.");

        System.out.println("\n--- PILA DE DEVOLUCIONES (la mas reciente primero) ---");
        if (r.getDevoluciones().isEmpty()) System.out.println("Sin devoluciones.");
        for (ContratoRenting k : r.getDevoluciones()) imprimir(k);

        System.out.println("\nTOTAL DE INGRESOS GENERADOS POR RENTING: $" + r.totalIngresos());
        System.out.println("===============================================");
    }
}


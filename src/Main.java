import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] arg) {
		// PEDICION DE DATOS
		generarDatos();

	}

	static void generarDatos() {
		// ESTABLECIENDO LOS TIPOS DE DATOS
		float sueldo;
		byte horas;

		// ENTRADA DE LOS METODOS PARA VALIDAR EL TIPO Y RANGO DE LOS DATOS
		horas = entradaHoras();
		sueldo = entradaSueldo();

		// METODO PARA EL CALCULO DE LA COMISION
		asignacionComision(sueldo, horas);
	}

	static byte entradaHoras() {
		String strHoras;
		int errorId = 1;
		byte horas = 0;

		// INGRESO DE HORAS LABORALES
		do {
			strHoras = JOptionPane.showInputDialog(null, "Ingrese el numero de Horas Trabajadas", "HORAS LABORALES",
					JOptionPane.INFORMATION_MESSAGE);
		} while (validacion(strHoras, 1, errorId) != true);

		// PARSE(CONVERSION) DE STRING A BYTE
		horas = Byte.parseByte(strHoras);

		// VALIDACION DEL RANGO LIMITE DE HORAS
		if (horas < 0 || horas > 70) {
			error(3);
		}
		return horas;
	}

	static float entradaSueldo() {
		String strSueldo;
		int errorId = 2;
		float sueldo = 0.0f;

		// INGRESO DEL SUELDO BASE
		do {
			strSueldo = JOptionPane.showInputDialog(null, "Ingrese el sueldo base", "SUELDO BASE",
					JOptionPane.INFORMATION_MESSAGE);
		} while (validacion(strSueldo, 2, errorId) != true);

		// PARSE(CONVERSION) DE STRING A FLOAT
		sueldo = Float.parseFloat(strSueldo);

		// VALIDACION DEL RANGO LIMITE DE SUELDO
		if (sueldo < 0 || sueldo > 60) {
			error(4);
		}
		return sueldo;
	}

	static boolean validacion(String cadena, int id, int errorId) {
		boolean respuesta;

		if (cadena == null) {
			error(0);
		}

		// CAPTURA DE ERRORES
		try {
			switch (id) {
			case 1: // VALIDACION PARA DATOS BYTE
				Byte.parseByte(cadena);
				break;

			case 2: // VALIDADCION PARA DATOS FLOAT
				Float.parseFloat(cadena);
				break;

			default:
				break;
			}
			respuesta = true;
		} catch (NumberFormatException e) {
			error(errorId);
			respuesta = false;
		}

		return respuesta;
	}

	// METODO PARA MOSTRAR LOS ERRORES
	static void error(int codigoError) {
		switch (codigoError) {
		case 1: {
			JOptionPane.showMessageDialog(null, "ERROR EN LA ASIGNACION DE LAS HORAS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		case 2: {
			JOptionPane.showMessageDialog(null, "ERROR EN LA ASIGNACION DEL SUELDO", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		case 3: {
			JOptionPane.showMessageDialog(null, "ERROR EN EL RANGO DE HORAS", "ERROR", JOptionPane.ERROR_MESSAGE);
			entradaHoras();
			break;
		}
		case 4: {
			JOptionPane.showMessageDialog(null, "ERROR EN EL RANGO DE SUELDO", "ERROR", JOptionPane.ERROR_MESSAGE);
			entradaSueldo();
			break;
		}
		default:
			System.exit(0);
		}

	}

	// METODO PARA LA ASIGNACION DE COMISION EXTRA EN BASE A HORAS
	static void asignacionComision(float sueldoBase, byte horas) {
		// VERIFICACION DE LAS HORAS PARA ASIGNAR SU RESPECTIVA COMISION
		if (horas > 40) {
			calcularSalario(horas, sueldoBase, 40, 1.5f);
		} else if (horas > 50) {
			calcularSalario(horas, sueldoBase, 40, 2.0f);
		} else {
			calcularSalario(horas, sueldoBase);
		}
	}

	// **METODOS SOBRECARGADOS**
	// METODO PARA SALARIO SIN COMISION
	static void calcularSalario(byte horas, float sueldoBase) {
		float salario;
		salario = sueldoBase * horas;
		mostrarSalarioFinal(salario);
	}

	// METODO PARA SALARIO CON COMISION
	static void calcularSalario(byte horas, float sueldoBase, int horaBase, float porcentaje) {
		float salario, comision;
		horaBase = horas - horaBase;
		horas -= horaBase;
		comision = (sueldoBase * porcentaje) * horaBase;
		salario = sueldoBase * horas + comision;
		mostrarSalarioFinal(salario);
	}

	// METODO PARA MOSTRAR SALARIO
	static void mostrarSalarioFinal(float salario) {
		JOptionPane.showMessageDialog(null, "SU SALARIO FINAL ES $" + salario, "PLANILLA",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

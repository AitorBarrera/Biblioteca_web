package test;

import java.util.ArrayList;
import java.util.List;

import dao.DaoSocio;
import entidades.Socio;

public class testDaoSocio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoSocio daoA = new DaoSocio();
		
		Socio so = new Socio();
		
//		so.setEmail("SOCIO66@AZARQUIEL.ES");
//		so.setNombre("SOCIO 66");
//		so.setDireccion("C/ SOCIO 66");
//		so.setVersion(1);
//		
//		daoA.addSocio(so);
	
//		System.out.println(daoA.getSocio());
		
//		Socio socioABorrar = daoA.getSocio(19);
//		System.out.println(socioABorrar);
//		daoA.removeSocio(socioABorrar);
		
//		Socio socioAActualizar = daoA.getSocio(25);
//		socioAActualizar.setNombre("kio");
//		
//		daoA.updateSocio(socioAActualizar);
		
//		System.out.println(daoA.getSocio("7"));
		
//		try {
//			List<Socio> listaSocios = daoA.listadoSocios(0, 4);
//			
//			for (Socio socio : listaSocios) {
//				System.out.println(socio);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(daoA.listadoSocios(0, 5));
		
		try {
			System.out.println(daoA.getSocio("s"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

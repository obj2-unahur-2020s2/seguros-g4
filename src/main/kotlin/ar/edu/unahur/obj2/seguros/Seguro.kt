package ar.edu.unahur.obj2.seguros

interface Seguro {
  fun costoPara(vehiculo: Vehiculo): Double
  fun puedeSerContratado(vehiculo: Vehiculo): Boolean


}

class ContraTerceros : Seguro {
  override fun costoPara(vehiculo: Vehiculo)=
          vehiculo.costoDeSeguroContraTercero()
  override fun puedeSerContratado(vehiculo: Vehiculo): Boolean =
          true
}

class RoboHurto: Seguro {
  override fun costoPara(vehiculo: Vehiculo)=
          vehiculo.costoDeSeguroContraRoboHurto()
  override fun puedeSerContratado(vehiculo: Vehiculo): Boolean =
          vehiculo.noEsAntiguo()
}

class TodoRiesgo: Seguro {
  override fun costoPara(vehiculo: Vehiculo)=
          vehiculo.costoDeSeguroTodoRiesgo()
  override fun puedeSerContratado(vehiculo: Vehiculo): Boolean =
          vehiculo.noEsDeUsoParticular()
}

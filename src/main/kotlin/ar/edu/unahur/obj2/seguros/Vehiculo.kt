package ar.edu.unahur.obj2.seguros

abstract class Vehiculo(val valor: Int, val antiguedad: Int) {
    var segurosContratados = mutableListOf<Seguro>()
  abstract fun puedeContratar(seguro: Seguro): Boolean
    fun costoTotal()=
        segurosContratados.sumBy { it.costoPara(this).toInt() }

  fun contratar(seguro: Seguro) {
    if(seguro.puedeSerContratado(this)){
        segurosContratados.add(seguro)
    }
      else{
        throw Exception("no se puede contratar el seguro")
    }
  }

  abstract fun noEsAntiguo(): Boolean
  abstract fun noEsDeUsoParticular(): Boolean
  abstract fun costoDeSeguroContraTercero() : Double
  open fun costoDeSeguroContraRoboHurto() =
          if(this.noEsAntiguo()){
            this.valor * 0.05
          }
          else{
            throw Exception("no hay costo porque no se puede asegurar")
          }
  open fun costoDeSeguroTodoRiesgo() :Double=
          throw Exception("solamente es para autos")
}


class Auto(valor: Int, antiguedad: Int,val fechaFabricacion:Int) : Vehiculo(valor, antiguedad) {
  override fun puedeContratar(seguro: Seguro)=
    seguro.puedeSerContratado(this)

  override fun noEsAntiguo()=
    true

  override fun noEsDeUsoParticular()=
    true

  override fun costoDeSeguroContraTercero()=
          this.valor * 0.01

  override fun costoDeSeguroContraRoboHurto()=
            if(this.fechaFabricacion > 1995){
              this.valor * 0.03
            }
            else{
              this.valor * 0.05
            }

  override fun costoDeSeguroTodoRiesgo()=
          this.valor * 0.07

}

class Camion(valor: Int, antiguedad: Int) : Vehiculo(valor, antiguedad) {
  override fun puedeContratar(seguro: Seguro)=
    seguro.puedeSerContratado(this)


  override fun noEsAntiguo()=
    this.antiguedad <= 10

  override fun noEsDeUsoParticular()=
    false

  override fun costoDeSeguroContraTercero()=
        if(this.noEsAntiguo()){
          this.valor * 0.015
        }
        else{
          this.valor * 0.02
        }
}

class Taxi(valor: Int, antiguedad: Int,val tieneInfracciones:Boolean) : Vehiculo(valor, antiguedad) {
  override fun puedeContratar(seguro: Seguro)=
    seguro.puedeSerContratado(this)

  override fun noEsAntiguo()=
    this.antiguedad <= 12

  override fun noEsDeUsoParticular()=
    false
  override fun costoDeSeguroContraTercero()=
          if(this.tieneInfracciones){
            (this.valor * 0.2) + 1000
          }
          else{
            this.valor * 0.2
          }


}





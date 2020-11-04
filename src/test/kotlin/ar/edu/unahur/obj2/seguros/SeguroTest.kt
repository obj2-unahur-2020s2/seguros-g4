package ar.edu.unahur.obj2.seguros

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeFalse
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.shouldBe

class SeguroTest : DescribeSpec({

  describe("Seguros") {
    val seguroContraTercero = ContraTerceros()
    val seguroRoboHurto = RoboHurto()
    val seguroTodoRiesgo = TodoRiesgo()
    val auto = Auto(200000,30,1990)
    val camionAntiguo = Camion(250000,11)
    val taxiAntiguo = Taxi(500000,14,true)
    val camionOKM =Camion(1300000,0)
    val taxiOKM =Taxi(1100000,0,false)
    val auto0KM = Auto(900000,0,2020)
    it("auto puede contratar contra tercero"){

      auto.puedeContratar(seguroContraTercero).shouldBeTrue()
    }
    it("auto puede contratar contra robo o hurto"){

      auto.puedeContratar(seguroRoboHurto).shouldBeTrue()
    }
    it("auto puede contratar contra seguro todo riesgo") {

      auto.puedeContratar(seguroTodoRiesgo).shouldBeTrue()
    }
    it("camionAntiguo puede contratar contra tercero"){
      camionAntiguo.puedeContratar(seguroContraTercero).shouldBeTrue()
    }
    it("camionAntiguo no puede contratar contra robo o hurto"){
      camionAntiguo.puedeContratar(seguroRoboHurto).shouldBeFalse()
    }
    it("camionOKM puede contratar contra robo o hurto"){

      camionOKM.puedeContratar(seguroRoboHurto).shouldBeTrue()
    }
    it("camionAntiguo ni camion0KM no pueden contratar contra contra todo riesgo"){
      camionAntiguo.puedeContratar(seguroTodoRiesgo).shouldBeFalse()

      camionOKM.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
    }
    it("taxiAntiguo puede contratar contra tercero"){
      taxiAntiguo.puedeContratar(seguroContraTercero).shouldBeTrue()
    }
    it("taxiAntiguo no puede contratar contra robo o hurto"){
      taxiAntiguo.puedeContratar(seguroRoboHurto).shouldBeFalse()
    }
    it("taxiOKM puede contratar contra robo o hurto"){

      taxiOKM.puedeContratar(seguroRoboHurto).shouldBeTrue()
    }
    it("taxiAntiguo ni taxi0KM no pueden contratar contra contra todo riesgo"){
      taxiAntiguo.puedeContratar(seguroTodoRiesgo).shouldBeFalse()

      taxiOKM.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
    }
    it("costo del seguro contra tercero para auto"){
      seguroContraTercero.costoPara(auto).shouldBe(2000)
    }
    it("costo del seguro contra tercero para camionAntiguo"){
      seguroContraTercero.costoPara(camionAntiguo).shouldBe(5000)
    }
    it("costo del seguro contra tercero para camionOKM"){
      seguroContraTercero.costoPara(camionOKM).shouldBe(19500)
    }
    it("costo del seguro contra tercero para taxiAntiguo con infracciones"){
      seguroContraTercero.costoPara(taxiAntiguo).shouldBe(101000.0)
    }
    it("costo del seguro contra tercero para taxiOKM sin infracciones"){
      seguroContraTercero.costoPara(taxiOKM).shouldBe(220000.0)
    }
    it("costo del seguro robo hurto para auto"){
      seguroRoboHurto.costoPara(auto).shouldBe(10000.0)
    }
    it("costo del seguro robo hurto para auto0KM"){
      seguroRoboHurto.costoPara(auto0KM).shouldBe(27000.0)
    }
    it("costo del seguro robo hurto para camion0KM"){
      seguroRoboHurto.costoPara(camionOKM).shouldBe(65000)
    }
    it("costo del seguro todo riesgo para auto"){
      seguroTodoRiesgo.costoPara(auto).shouldBe(14000.000000000002)
    }
    it("camionAntiguio no puede contratar el servio"){

      shouldThrowAny { camionAntiguo.contratar(seguroRoboHurto) }
    }
    it("suma total que paga el auto de seguro"){
      auto.contratar(seguroContraTercero)
      auto.contratar(seguroRoboHurto)
      auto.contratar(seguroTodoRiesgo)
      auto.costoTotal().shouldBe(26000)
    }
  }


})

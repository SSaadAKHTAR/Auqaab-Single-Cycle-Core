package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
class Ex3Tester extends FreeSpec with ChiselScalatestTester {
  "lab5ex3 Tester file" in {
    test(new Operator(2 , UInt(16. W ) ) ( _ + _ ) ){a =>
        a.io.in(0).poke(20.U)
        a.io.in(1).poke(20.U)
        // a.io.in2.poke(12.U)
        // a.io.sel.poke(1.B)
        a.io.out(1).expect(40.U)
         
    a.clock.step(1)

    } 
  }
}



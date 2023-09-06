package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec

class Tak2Test extends FreeSpec with ChiselScalatestTester {
  "Lab6 t2 tester file" in {
    test(new counter_with_xor) { a =>
    
      a.clock.step(100)
       a.io.out.expect(0.U)
    }
  }
}



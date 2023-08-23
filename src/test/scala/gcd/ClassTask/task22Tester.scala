package ClassTask

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec

class task22Tester extends FreeSpec with ChiselScalatestTester {
  "TxRx modules should transmit data correctly" in {
    test(new Main) { a =>
      a.io.txinp.poke(true.B)
      a.io.busyInp.poke(0.B)
      
      a.clock.step(10)
      a.io.datacheck.expect(0.U)

       
    }
  }
}

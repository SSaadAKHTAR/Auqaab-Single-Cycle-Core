package Lab3


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Encoder Tester file" in {
    test(new Encoder4to2){a =>
    // a.io.reload.expect(0.B)
     
     a.io.in.poke("b0010".U)
      a.io.out.expect("b01".U)
    //   b.io.data_in.poke(5.U)
        a.clock.step(10)

    }
  }
}

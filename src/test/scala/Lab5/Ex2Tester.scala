package Lab5


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Ex2Tester extends FreeSpec with ChiselScalatestTester {
  "lab5 Tester file" in {
    test(new eMux(UInt(32.W))){a =>
        a.io.in1.poke(20.U)
        a.io.in2.poke(12.U)
        a.io.sel.poke(1.B)
        a.io.out.expect(20.U)
         
    a.clock.step(1)

    }
  }
}



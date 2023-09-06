package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Ex3Tester extends FreeSpec with ChiselScalatestTester {
  "lab6 Ex3 Tester file" in {
    test(new oneShotTimer){a =>
        a.io.reload.poke(1.B)
        a.io.din.poke(4.U)
        // a.io.done.poke(0.B)
       
        a.clock.step(10)
         a.io.out.expect(0.B)

        
    } 
  }
}



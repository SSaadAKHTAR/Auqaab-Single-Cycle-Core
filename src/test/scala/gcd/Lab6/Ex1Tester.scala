package Lab6



import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Ex1Tester extends FreeSpec with ChiselScalatestTester {
  "lab6 Ex1 Tester file" in {
    test(new shift_register){a =>
        a.io.in.poke("b0000".U)
        
        a.clock.step()
        a.io.out.expect("b0000".U)
    } 
  }
}


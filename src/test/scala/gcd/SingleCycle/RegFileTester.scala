package SingleCycle

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Reg_FileTester extends FreeSpec with ChiselScalatestTester {
  "regfile Tester file" in {
    test(new Reg_File){a =>
        // a.io.data.poke(28.S)
        a.io.rd.poke(1.U)
        a.io.rs1.poke(2.U)
        a.io.rs2.poke(3.U)
        a.io.wr.poke(1.B)
        a.io.Rd.poke(28.S)
        a.clock.step()
        // a.io.chk.expect(28.S)
        a.io.Rs1.expect(0.S)
        a.io.Rs2.expect(0.S)
       
        
      

       
    } 
  }
}



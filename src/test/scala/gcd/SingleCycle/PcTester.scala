package SingleCycle
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class PCTester extends FreeSpec with ChiselScalatestTester {
  "regfile Tester file" in {
    test(new ProgramCounter){a =>
      //  a.io.addr.poke(4.U)
       a.clock.step()
       a.io.new_addr.expect(8.U)
       
        
      

       
    } 
  }
}



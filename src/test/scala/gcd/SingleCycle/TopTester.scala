package SingleCycle
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class TopTester extends FreeSpec with ChiselScalatestTester {
  "Top Tester file" in {
    test(new Top){a =>
        a.clock.step(20)
        a.io.din.poke(2.S)
        a.io.out.expect(0.S)
        // a.io.eout.expect(2.U)
        
       
        
      

       
    } 
  }
}



package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class DataPathTester  extends FreeSpec with ChiselScalatestTester {
"control Tester" in{
    test ( new DataPath ) { c =>
        c.clock.step(250)
        c.io.out.expect(0.S)
        

    }
}
}


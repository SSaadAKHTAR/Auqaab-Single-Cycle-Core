package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class DataMemTester  extends FreeSpec with ChiselScalatestTester {
"D Tester" in{
    test ( new  Datamem) { c =>
        c.io.Wen.poke(1.B)
        c.io.Din.poke(10.S)
        c.io.addr.poke(1.U)
        c.io.fun3.poke(0.U)
        c.io.enable.poke(0.B)
        c.clock.step(1)
        c.io.Dout.expect(0.S)
        
        

    }}}
package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class IMMTester  extends FreeSpec with ChiselScalatestTester {
"imm Tester" in{
    test ( new ImmdValGen1 ) { c =>
        c.io.imm.poke("b000010010011".U)
        c.clock.step()
        c.io.immd_se.expect(4.U)




    }}}


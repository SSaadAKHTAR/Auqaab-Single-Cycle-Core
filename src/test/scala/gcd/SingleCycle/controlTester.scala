package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class ControlUnitTester  extends FreeSpec with ChiselScalatestTester {
"control Tester" in{
    test ( new contolUnit ) { c =>
        c.io.instruction.poke(0x003100b3.U)
        c.clock.step()
        c.io.iform.expect(0.B)
        c.io.rform.expect(1.B)
        c.io.rd.expect(1.U)
        c.io.rs1.expect(2.U)
        c.io.rs2.expect(3.U)
        c.io.aluop.expect(0.U)


    }}}
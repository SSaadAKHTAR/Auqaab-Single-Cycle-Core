package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
class TestALUS  extends FreeSpec with ChiselScalatestTester {
"ALUS Test" in {
test ( new ALUS ) { c =>
    c.io.in_A.poke(1.S)
    c.io.in_B.poke(2.S)
    c.io.alu_op.poke(0.U)
    c.clock.step(1)
    c.io.out.expect(3.S)
    





}}}
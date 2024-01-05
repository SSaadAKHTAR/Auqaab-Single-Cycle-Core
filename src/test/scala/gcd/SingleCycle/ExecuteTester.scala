package SingleCycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._

class ExecuteTester extends FreeSpec with ChiselScalatestTester {
  "execute Tester" in {
    test(new execute) { c =>
      // Test Case 1: Basic test with default values
      c.io.iform.poke(false.B)
      c.io.rform.poke(false.B)
      c.io.sform.poke(false.B)
      c.io.bform.poke(false.B)
      c.io.luiform.poke(false.B)
      c.io.Jalform.poke(false.B)
      c.io.jalrform.poke(false.B)
      c.io.lform.poke(false.B)
      c.io.Auipc.poke(false.B)
      c.io.lui_jal_jalr_auipc_imm.poke(0.S)
      c.io.immBits.poke(0.U)
      c.io.mem_wr_en.poke(false.B)
      c.io.wr_back.poke(0.U)
      c.io.br_fun3.poke(0.U)
      c.io.load_storefun.poke(0.U)
      c.io.pcsel.poke(false.B)
      c.io.mem_r_en.poke(false.B)
      c.io.rs1.poke(0.S)
      c.io.rs2.poke(0.S)
      c.io.aluop.poke(0.U)
      c.io.pc_out.poke(0.U)
      c.io.imm_out.poke(0.U)
      c.io.RS1.poke(0.U)
      c.io.RS2.poke(0.U)
      c.io.Rd.poke(0.U)
      c.io.RegWr_en.poke(false.B)

      c.clock.step(5)

      // Add more test cases as needed

    }
  }
}

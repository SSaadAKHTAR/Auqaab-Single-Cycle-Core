package ClassTask

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec

class task22Tester extends FreeSpec with ChiselScalatestTester {
  "txrx main modules" in {
    test(new TxRxStateMachine) { dut =>
      // Reset state
      //dut.reset.poke(false.B)

      dut.io.busy.poke(0.B)
      dut.io.tx.poke(1.B)
      
      
      dut.clock.step(3)
      dut.io.data.expect(4.U)
      // dut.io.tx.poke(true.B)
      // dut.io.ready.poke(true.B)
      // dut.io.valid.expect(false.B)
      // dut.io.data.expect(0.U)
      // dut.clock.step(3)

      // Test idle state
      // dut.io.valid.expect(false.B)
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(false.B)

      //Transition to tx state
      // dut.io.tx.poke(false.B)
      // dut.clock.step()
      // dut.io.valid.expect(false.B)
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(false.B)

      // Transition to valid state
      // dut.io.busy.poke(false.B)
      // dut.io.tx.poke(true.B) 
      // dut.clock.step()
      // dut.io.valid.expect(true.B)
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(true.B)

      // // Transfer data
      // dut.clock.step()
      // dut.io.valid.expect(false.B)
      // // Set your expected data value here
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(true.B)

      // // Transition back to tx state
      // dut.clock.step()
      // dut.io.valid.expect(true.B)
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(true.B)

      // // Transition to valid state again
      // //dut.io.ready.poke(true.B)
      // dut.clock.step()
      // dut.io.valid.expect(false.B)
      // // Set your expected data value here
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(true.B)

      // // Transfer data again
      // dut.clock.step()
      // dut.io.valid.expect(true.B)
      // // Set your expected data value here
      // dut.io.data.expect(0.U)
      // dut.io.ready.expect(true.B)
    }
  }
}
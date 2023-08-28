package ClassTask
import chisel3 . _
import chisel3.util._

class TxRxStateMachine extends Module {
  val io = IO(new Bundle {
    val tx = Input(Bool())
    val busy = Input(Bool())
    // val valid = Output(Bool())
    val data = Output(UInt(8.W))
    // val ready = Output(Bool())
  })

  val txModule = Module(new TxModule)
  val rxModule = Module(new RxModule)

  txModule.io.tx := io.tx
   rxModule.io.busy := io.busy
  txModule.io.ready := rxModule.io.ready

  rxModule.io.data := txModule.io.data  
  io.data := txModule.io.data
 
  rxModule.io.valid := txModule.io.valid
  //   io.valid := txModule.io.valid
  // rxModule.io.data := io.data
//   io.ready := rxModule.io.ready
}


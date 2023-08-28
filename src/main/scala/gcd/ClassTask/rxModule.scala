package ClassTask
import chisel3 . _
import chisel3.util._

class RxModule extends Module {
  val io = IO(new Bundle {
    val busy = Input(Bool())
    val valid = Input(Bool())
    val data = Input(UInt(8.W))
    val ready = Output(Bool())
  })

  val readyReg = RegInit(false.B)
  val data_reg = RegInit(0.U(8.W))

  when(io.busy) {
    readyReg := 0.B
  }.otherwise {
    readyReg := 1.B
  }

  when(io.valid === true.B && io.ready === true.B){
    data_reg := io.data
  }
  io.ready := readyReg
}
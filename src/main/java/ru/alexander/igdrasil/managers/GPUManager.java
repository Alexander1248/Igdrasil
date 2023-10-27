package ru.alexander.igdrasil.managers;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Pointer;
import ru.alexander.igdrasil.vectors.Vector3I;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.cuda.CU.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GPUManager {

    private final Map<String, PointerBuffer> modules = new HashMap<>();
    private final Map<String, PointerBuffer> functions = new HashMap<>();

    public GPUManager() {
        cuInit(0);

        IntBuffer device = IntBuffer.allocate(Pointer.POINTER_SIZE);
        cuDeviceGet(device, 0);

        PointerBuffer context = PointerBuffer.allocateDirect(Pointer.POINTER_SIZE);
        cuCtxCreate(context, 0, device.get());
    }

    public void addModule(String moduleTag, String filename) {
        PointerBuffer module = PointerBuffer.allocateDirect(Pointer.POINTER_SIZE);
        cuModuleLoad(module, filename);
        modules.put(moduleTag, module);
    }
    public void addFunction(String functionTag, String moduleName, String functionName) {
        PointerBuffer function = PointerBuffer.allocateDirect(Pointer.POINTER_SIZE);
        cuModuleGetFunction(function, modules.get(moduleName).get(), functionName);
        functions.put(functionTag, function);
    }



    public PointerBuffer createPointer(int size) {
        PointerBuffer pointer = PointerBuffer.allocateDirect(size);
        cuMemAlloc(pointer, size);
        return pointer;
    }
    public void freePointer(PointerBuffer pointer) {
        cuMemFree(pointer.get());
    }

    public void executeFunction(String functionTag,
                                Vector3I global, Vector3I local,
                                PointerBuffer parameters) {
        cuLaunchKernel(functions.get(functionTag).get(),
                global.x, global.y, global.z,
                local.x, local.y, local.z,
                0, NULL, parameters, null);
    }
}

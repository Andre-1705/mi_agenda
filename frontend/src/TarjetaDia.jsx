import { useState } from 'react';

function TarjetaDia({ nombre, tareas = [], diaIndex, onEliminar, onModificar }) {
    const [editingIndex, setEditingIndex] = useState(null);
    const [editHora, setEditHora] = useState("");
    const [editTexto, setEditTexto] = useState("");

    const startEdit = (tarea, index) => {
        setEditingIndex(index);
        setEditHora(tarea.hora || "");
        setEditTexto(tarea.texto || "");
    };

    // Cierra el modo edición y limpia campos
    const finishEdit = () => {
        setEditingIndex(null);
        setEditHora("");
        setEditTexto("");
    };

    const saveEdit = (index) => {
        // Llama al callback con los nuevos valores
        onModificar?.(diaIndex, index, { hora: editHora, texto: editTexto });
        finishEdit();
    };

    return (
        <div className="bg-white p-6 rounded-lg shadow-md border border-gray-200">
            <h2 className="text-xl font-bold text-blue-600 mb-4 border-b pb-2">
                {nombre}
            </h2>
            <ul>
                {tareas.length === 0 ? (
                    <li className="text-gray-500 italic">No hay tareas para este día</li>
                ) : (
                    tareas.map((tarea, index) => (
                        <li key={index} className="text-gray-700 bg-gray-50 p-2 rounded flex justify-between items-center">
                            {editingIndex === index ? (
                                <div className="flex-1 flex gap-2 items-center">
                                    <input value={editHora} onChange={(e) => setEditHora(e.target.value)} className="p-1 border rounded w-24" />
                                    <input value={editTexto} onChange={(e) => setEditTexto(e.target.value)} className="p-1 border rounded flex-1" />
                                    <button onClick={() => saveEdit(index)} className="text-green-600 font-bold ml-2">Guardar</button>
                                </div>
                            ) : (
                                <>
                                    <span>{tarea.hora} - {tarea.texto}</span>
                                    <div className="flex gap-2">
                                        <button
                                            type="button"
                                            onClick={() => startEdit(tarea, index)}
                                            className="text-blue-500 hover:text-blue-700 text-sm font-bold"
                                            title="Modificar"
                                        >
                                            ✏️
                                        </button>
                                        <button
                                            type="button"
                                            onClick={() => onEliminar?.(diaIndex, index)}
                                            className="text-red-500 hover:text-red-700 text-sm font-bold"
                                            title="Eliminar"
                                        >
                                            X
                                        </button>
                                    </div>
                                </>
                            )}
                        </li>
                    ))
                )}
            </ul>
        </div>
    )
}
export default TarjetaDia;
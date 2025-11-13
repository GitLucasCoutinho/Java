/**
 * @file Componente para selecionar os dias da semana para tarefas recorrentes.
 */
"use client";

import { ToggleGroup, ToggleGroupItem } from "@/components/ui/toggle-group";

// Define os dias da semana para o seletor.
const daysOfWeek = [
  { id: 'SUN', label: 'D' },
  { id: 'MON', label: 'S' },
  { id: 'TUE', label: 'T' },
  { id: 'WED', label: 'Q' },
  { id: 'THU', label: 'Q' },
  { id: 'FRI', label: 'S' },
  { id: 'SAT', label: 'S' },
];

/**
 * Propriedades para o componente DaySelector.
 */
type DaySelectorProps = {
  value: string[];
  onValueChange: (value: string[]) => void;
};

/**
 * Um componente que permite ao usuário selecionar um ou mais dias da semana.
 * @param {DaySelectorProps} props - As propriedades do componente.
 */
export function DaySelector({ value, onValueChange }: DaySelectorProps) {
  return (
    <ToggleGroup 
      type="multiple"
      variant="outline"
      value={value}
      onValueChange={onValueChange}
      className="grid grid-cols-7 gap-1"
    >
      {daysOfWeek.map(day => (
        <ToggleGroupItem 
          key={day.id} 
          value={day.id} 
          aria-label={`Selecionar ${day.label}`}
          className="h-9 w-9 rounded-full data-[state=on]:bg-primary data-[state=on]:text-primary-foreground"
        >
          {day.label}
        </ToggleGroupItem>
      ))}
    </ToggleGroup>
  );
}

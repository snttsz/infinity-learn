import { Component, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ver-curso',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ver-curso.component.html',
  styleUrl: './ver-curso.component.css'
})
export class VerCursoComponent 
{
  @Output() buttonClick = new EventEmitter<void>();

  onVerCursoClick(): void
  {
    this.buttonClick.emit();
  }
}

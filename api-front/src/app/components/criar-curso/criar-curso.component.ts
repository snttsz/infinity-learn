import { Component, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-criar-curso',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './criar-curso.component.html',
  styleUrl: './criar-curso.component.css'
})
export class CriarCursoComponent 
{
  @Output() buttonClick = new EventEmitter<void>();

  onCriarCursoClick(): void
  {
    this.buttonClick.emit();
  }
}

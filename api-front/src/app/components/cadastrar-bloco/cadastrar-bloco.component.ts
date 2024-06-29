import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-cadastrar-bloco',
  standalone: true,
  imports: [],
  templateUrl: './cadastrar-bloco.component.html',
  styleUrl: './cadastrar-bloco.component.css'
})

export class CadastrarBlocoComponent 
{
  @Output() buttonClick = new EventEmitter<void>();

  onVoltarButtonClick(): void
  {
    this.buttonClick.emit();
  }
}

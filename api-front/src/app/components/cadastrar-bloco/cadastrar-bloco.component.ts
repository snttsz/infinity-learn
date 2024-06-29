import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastrar-bloco',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cadastrar-bloco.component.html',
  styleUrl: './cadastrar-bloco.component.css'
})

export class CadastrarBlocoComponent 
{
  @Output() buttonClick = new EventEmitter<void>();

  partOfRegister: number = 1;

  onVoltarButtonClick(): void
  {
    if (this.partOfRegister == 1)
    {
      this.buttonClick.emit();
    }
    else
    {
      this.partOfRegister--;
    }
  }

  onContinuarButtonClick(): void
  {
    if (this.validarDadosInseridos())
    {
      this.partOfRegister++;
    }
    else
    {
      
    }
  }

  validarDadosInseridos(): boolean
  {
    return true;
  }
}

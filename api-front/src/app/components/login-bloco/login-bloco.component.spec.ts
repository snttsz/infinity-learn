import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginBlocoComponent } from './login-bloco.component';

describe('LoginBlocoComponent', () => {
  let component: LoginBlocoComponent;
  let fixture: ComponentFixture<LoginBlocoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginBlocoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginBlocoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

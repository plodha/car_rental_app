import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogComponentPageComponent } from './dialog-component-page.component';

describe('DialogComponentPageComponent', () => {
  let component: DialogComponentPageComponent;
  let fixture: ComponentFixture<DialogComponentPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogComponentPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogComponentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

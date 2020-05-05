import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLocationPageComponent } from './edit-location-page.component';

describe('EditLocationPageComponent', () => {
  let component: EditLocationPageComponent;
  let fixture: ComponentFixture<EditLocationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLocationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLocationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

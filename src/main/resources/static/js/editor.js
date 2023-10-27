const editor = document.getElementById('editor');
const btnBold = document.getElementById('btn-bold');
const btnItalic = document.getElementById('btn-italic');
const btnUnderline = document.getElementById('btn-underline');
const btnStrike = document.getElementById('btn-strike');
const btnOrderedList = document.getElementById('btn-ordered-list');
const btnUnorderedList = document.getElementById('btn-unordered-list');
const btnImage = document.getElementById('btn-image');
const imageSelector = document.getElementById('img-selector');
const fontSizeSelector = document.getElementById('select-font-size');
const fontNameSelector = document.getElementById('select-font-name');
const selectFontColor = document.getElementById('select-font-color');
const selectFontBackground = document.getElementById('select-font-background');

// 폰트 사이즈에 따른 값을 찾기 위해서 넣은 배열
const fontSizeList = [10, 13, 16, 18, 24, 32, 48];


btnBold.addEventListener('click', function () {
  setStyle('bold');
});

btnItalic.addEventListener('click', function () {
  setStyle('italic');
});

btnUnderline.addEventListener('click', function () {
  setStyle('underline');
});

btnStrike.addEventListener('click', function () {
  setStyle('strikeThrough')
});

btnOrderedList.addEventListener('click', function () {
  setStyle('insertOrderedList');
});

btnUnorderedList.addEventListener('click', function () {
  setStyle('insertUnorderedList');
});
btnImage.addEventListener('click', function () {
  imageSelector.click();
});

imageSelector.addEventListener('change', function (e) {
  const files = e.target.files;
  if (!!files) {
    insertImageDate(files[0]);
  }
});
editor.addEventListener('keydown', function () {
  checkStyle();
});

editor.addEventListener('mousedown', function () {
  checkStyle();
});
fontSizeSelector.addEventListener('change', function () {
  changeFontSize(this.value);
});
fontNameSelector.addEventListener('change', function () {
  changeFontName(this.value);
});
selectFontColor.addEventListener('change', function () {
  setFontColor(this.value);
});

selectFontBackground.addEventListener('change', function () {
  setFontBackground(this.value);
});

function setFontColor(color) {
  document.execCommand('foreColor', false, color);
  focusEditor();
}

function setFontBackground(color) {
  document.execCommand('hiliteColor', false, color);
  focusEditor();
}


function setStyle(style) {
  document.execCommand(style);
  focusEditor();
  checkStyle();
  // reportFont();
}

// 버튼 클릭 시 에디터가 포커스를 잃기 때문에 다시 에디터에 포커스를 해줌
function focusEditor() {
  editor.focus({ preventScroll: true });
}
function insertImageDate(file) {
  const reader = new FileReader();
  reader.addEventListener('load', function (e) {
    focusEditor();
    document.execCommand('insertImage', false, `${reader.result}`);
  });
  reader.readAsDataURL(file);
}

function checkStyle() {
  if (isStyle('bold')) {
    btnBold.classList.add('active');
  } else {
    btnBold.classList.remove('active');
  }
  if (isStyle('italic')) {
    btnItalic.classList.add('active');
  } else {
    btnItalic.classList.remove('active');
  }
  if (isStyle('underline')) {
    btnUnderline.classList.add('active');
  } else {
    btnUnderline.classList.remove('active');
  }
  if (isStyle('strikeThrough')) {
    btnStrike.classList.add('active');
  } else {
    btnStrike.classList.remove('active');
  }
  if (isStyle('insertOrderedList')) {
    btnOrderedList.classList.add('active');
  } else {
    btnOrderedList.classList.remove('active');
  }
  if (isStyle('insertUnorderedList')) {
    btnUnorderedList.classList.add('active');
  } else {
    btnUnorderedList.classList.remove('active');
  }
}

function isStyle(style) {
  return document.queryCommandState(style);
}
function changeFontName(name) {
  document.execCommand('fontName', false, name);
  focusEditor();
}

function changeFontSize(size) {
  document.execCommand('fontSize', false, size);
  focusEditor();
}

// 폰트 정보 가져오는 코드 참고 : https://stackoverflow.com/questions/8770008/contenteditable-get-current-font-color-size
function getComputedStyleProperty(el, propName) {
  if (window.getComputedStyle) {
    return window.getComputedStyle(el, null)[propName];
  } else if (el.currentStyle) {
    return el.currentStyle[propName];
  }
}

function reportFont() {
  let containerEl, sel;
  if (window.getSelection) {
    sel = window.getSelection();
    if (sel.rangeCount) {
      containerEl = sel.getRangeAt(0).commonAncestorContainer;
      if (containerEl.nodeType === 3) {
        containerEl = containerEl.parentNode;
      }
    }
  } else if ((sel = document.selection) && sel.type !== 'Control') {
    containerEl = sel.createRange().parentElement();
  }

  if (containerEl) {
    const fontSize = getComputedStyleProperty(containerEl, 'fontSize');
    const fontName = getComputedStyleProperty(containerEl, 'fontFamily');
    const size = parseInt(fontSize.replace('px', ''));
    fontSizeSelector.value = fontSizeList.indexOf(size) + 1;
    // fontName이 문자열 "폰트명"으로 오기 때문에 "를 제거해주는 코드 추가
    fontNameSelector.value = fontName.replaceAll('"', '')

    const fontColor = getComputedStyleProperty(containerEl, 'color');
    const backgroundColor = getComputedStyleProperty(containerEl, 'backgroundColor');

    fontColorSelector.value = rgbToHex(fontColor).toUpperCase();
    // 기본 배경색은 rgba(0, 0, 0, 0)
    if (backgroundColor === 'rgba(0, 0, 0, 0)') {
      fontBackgroundSelector.value = backgroundColor;
    } else {
      fontBackgroundSelector.value = rgbToHex(backgroundColor).toUpperCase();
    }
  }
}
// 폰트 색상 rgb to hex, hex to rgb
// 참고 : https://stackoverflow.com/questions/5623838/rgb-to-hex-and-hex-to-rgb
function componentToHex(c) {
  const hex = parseInt(c).toString(16);
  console.log(hex);
  return hex.length == 1 ? '0' + hex : hex;
}

function rgbToHex(color) {
  // rgb(r, g, b)에서 색상값만 뽑아 내기 위해서 rgb() 제거
  const temp = color.replace(/[^0-9,]/g, '');
  // r,g,b만 남은 값을 ,로 [r,g,b] 배열로 변환
  const rgb = temp.split(',');
  return '#' + componentToHex(rgb[0]) + componentToHex(rgb[1]) + componentToHex(rgb[2]);
}